# Use of exec() is completely banned. Find all calls to exec().

import exec as safe_function

# ruleid: python-exec
safe_function(user_input)

# ruleid: python-exec
exec()

# ruleid: python-exec
exec("ls")

# ruleid: python-exec
exec(foo)

# ruleid: python-exec
exec(
    bar
)

# ruleid: python-exec
exec(foo, bar)

# ok: python-exec
some_exec(foo)

# ok: python-exec
# exec(foo)