from flask import Flask, request
import os
import init_db

app = flask.Flask(__name__)
init_db(app)

@app.route("/")
def index():

    filename = request.args.get("filename")
    mode = request.args.get("mode")

    # ruleid: flask-path-traversal
    os.open(filename)

    # ruleid: flask-path-traversal
    os.open(filename, mode=mode)

    # ok: flask-path-traversal
    os.open("hardcoded-filename.txt", mode=mode)

    # ruleid: flask-path-traversal
    os.open(mode=mode, path=filename)

    userId = request.args.get("id")
    user = app.db.get_or_404(User, userId)

    # ok: flask-path-traversal
    os.open(user.filename)
