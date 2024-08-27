from pyramid.authentication import AuthTktCookieHelper, AuthTktAuthenticationPolicy


### True positives ###

def bad1():
    # ruleid: pyramid-cookies
    authtkt = AuthTktCookieHelper(secret="test")

def bad2():
    # ruleid: pyramid-cookies
    authtkt = AuthTktCookieHelper(secret="test", samesite="none")


### True negatives ###

def good1():
    # ok: pyramid-cookies
    authtkt = AuthTktCookieHelper(secret="test", httponly=True)
