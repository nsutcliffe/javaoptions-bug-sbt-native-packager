This repository reproduces a bug found in `sbt-native-packager`, specifically with how javaOptions are set when using the plugin `JavaServerAppPackaging`. The bug can be observed by loading SBT Shell (I was using version 1.9.9)
and running the command `show javaOptions`. You will observe the following output:

```
[IJ]show javaOptions
[info] module2-nojavaoptions / Universal / javaOptions
[info] 	List()
[info] module1 / javaOptions
[info] 	List(-Dsome-property=true)
[info] module3 / javaOptions
[info] 	List(-Dsome-property=true)
[info] javaOptions
[info] 	List(-Dsome-property=true)
```

Note that `module2-nojavaoptions` has no javaOptions set, where as all other modules do. In particular, `module3` has the same definition as `module2-nojavaoptions`, except for we append an empty sequence to javaOptions, which
seems to make the module once again see the javaOption being set.
