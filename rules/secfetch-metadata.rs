#[macro_use] extern crate rocket;

use rocket_dyn_templates::Template;
use rocket_dyn_templates::context;
use rocket::http::{Cookie, CookieJar, SameSite};
use rocket::response::Redirect;

use rocket::request::Outcome;
use rocket::http::Status;
use rocket::request::{self, Request, FromRequest};


#[get("/")]
// ok: secfetch-metadata
fn index(cookies: &CookieJar<'_>) -> Template {
    let cookie_name = "balance";
    let mut balance = "";
    let mut greeting = "Welcome back!";

    if cookies.get(cookie_name).is_none() {
        let new_balance = "1000";
        let mut cookie = Cookie::new(cookie_name, new_balance);
        cookie.set_same_site(SameSite::None);
        cookies.add(cookie);
        balance = new_balance;
        greeting = "Hi! Welcome to your first time at our bank!";
    } else {
        let cookie = cookies.get(cookie_name);
        balance = cookie.unwrap().value();
    }

    Template::render("index", context! {
        balance: balance,
        greeting: greeting,
    })
}

#[get("/substract?<amount>")]
// ruleid: secfetch-metadata
fn substract(cookies: &CookieJar<'_>, amount: i32) -> Redirect {
    let option = cookies.get("balance");
    let cookie = option.unwrap();
    let string_value: &str = cookie.value();
    let balance = string_value.parse::<i32>().unwrap() - amount;

    let mut new_cookie = Cookie::new("balance", balance.to_string());
    new_cookie.set_same_site(SameSite::None);
    cookies.add(new_cookie);

    Redirect::to(uri!(index))
}

#[get("/multiply?<amount>")]
// ok: secfetch-metadata
fn multiply(cookies: &CookieJar<'_>, amount: i32, _sfm: SecFetchMetadata) -> Redirect {
    let option = cookies.get("balance");
    let cookie = option.unwrap();
    let string_value: &str = cookie.value();
    let balance = string_value.parse::<i32>().unwrap() * amount;

    let mut new_cookie = Cookie::new("balance", balance.to_string());
    new_cookie.set_same_site(SameSite::None);
    cookies.add(new_cookie);

    Redirect::to(uri!(index))
}

#[get("/add?<amount>")]
// ok: secfetch-metadata
fn add(cookies: &CookieJar<'_>, _sfm: SecFetchMetadata, amount: i32) -> Redirect {
    let option = cookies.get("balance");
    let cookie = option.unwrap();
    let string_value: &str = cookie.value();
    let balance = string_value.parse::<i32>().unwrap() + amount;

    let mut new_cookie = Cookie::new("balance", balance.to_string());
    new_cookie.set_same_site(SameSite::None);
    cookies.add(new_cookie);

    Redirect::to(uri!(index))
}

struct SecFetchMetadata{
}

#[rocket::async_trait]
impl<'r> FromRequest<'r> for SecFetchMetadata {
    type Error = ();

    async fn from_request(request: &'r Request<'_>) -> request::Outcome<Self, ()> {
        let site = request.headers().get_one("Sec-Fetch-Site");

        match site {
            // Allow same-origin and browser-initiated requests
            Some("same-origin") | Some("none") => {
                let sec_fetch_metadata = SecFetchMetadata {};
                Outcome::Success(sec_fetch_metadata)
            },
            _ => Outcome::Failure((Status::BadRequest, ()))
        }
    }
}

#[launch]
fn rocket() -> _ {
    rocket::build()
        .mount("/", routes![index, add, substract, multiply])
        .attach(Template::fairing())
}