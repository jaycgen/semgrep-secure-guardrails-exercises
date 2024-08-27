from fastapi import FastAPI

app = FastAPI()

@app.get("/files/{file_path:path}")
# ruleid: fastapi-path-traversal
async def read_file(file_path):
    open(file_path)

# ok: fastapi-path-traversal
@app.get("/files/{file_path:path}")
async def read_file(nope):
    open(nope)