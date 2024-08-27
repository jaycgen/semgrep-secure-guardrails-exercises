def f1():
    s = source()
    # ruleid: taint-demo
    sink(s)

def f2():
    s = source()
    s2 = "string concat propagates already" + s
    # ruleid: taint-demo
    sink(s2)

def f3():
    s = source()
    s = function_with_return_value(s)
    # ruleid: taint-demo
    sink(s)

def f4():
    s = source()
    s = sanitize(s)
    # pl: taint-demo
    sink(s)

def f5():
    s = source()
    d = MyDataStructure()
    d.add(s)
    # ruleid: taint-demo
    sink(d.retrieve(0))