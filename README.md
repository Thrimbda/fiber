# fiber
fiber will use all of your cpu to calculate fibonacci seq. Forever.

## Usage

runing fiber on the fly.

```bash
./mill fib.runLocal
```

build a executable jar file:

```bash
./mill fib.assembly
mv out/fib/assembly/dest/out.jar fib.jar

# run it just like this:
./fib.jar
```
