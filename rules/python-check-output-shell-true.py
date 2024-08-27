# Use of exec() is completely banned. Find all calls to exec().

import subprocess
import subprocess as safe_process

@app.route("/dns")
def page():

    hostname = request.values.get(hostname)
    cmd = 'nslookup' + hostname

    a_boolean_value = True

    # ruleid: python-check-output-shell-true
    subprocess.check_output(cmd, shell=True)
    # ruleid: python-check-output-shell-true
    safe_process.check_output(cmd, shell=True)
    # ruleid: python-check-output-shell-true
    subprocess.check_output(cmd, shell=a_boolean_value)

    # ok: python-check-output-shell-true   
    subprocess.check_output(cmd)

    # ok: python-check-output-shell-true  
    subprocess.check_output("nslookup hostname", shell=True)

    return