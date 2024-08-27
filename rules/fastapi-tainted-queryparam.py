from fastapi import FastAPI

app = FastAPI()

@app.post("/items/{pathparam}")
# ok: fastapi-tainted-queryparam
async def create_item(pathparam: str):
	sink(pathparam)

@app.get("/items")
# ruleid: fastapi-tainted-queryparam
async def create_item(queryparam: str):
	sink(queryparam)