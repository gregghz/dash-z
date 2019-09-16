Scalatest's `-z` flag does not work for specs included via `nestedSuites`.

With no flags
-------------

``` shell
$ sbt test
[info] StandaloneSpec:
[info] standalone
[info] - sample C test
[info] - sample D test
[info] RootSpec:
[info] root
[info] - root sample
[info] NestedSpecA:
[info] nested A
[info] - sample A test
[info] NestedSpecB:
[info] nested B
[info] - sample B test
[info] Run completed in 151 milliseconds.
[info] Total number of tests run: 5
[info] Suites: completed 4, aborted 0
[info] Tests: succeeded 5, failed 0, canceled 0, ignored 0, pending 0
[info] All tests passed.
```

With testOnly/-z selecting one test from the standalone spec
------------------------------------------------------------

``` shell
$ sbt testOnly *StandaloneSpec -- -z "sample D test"
[info] StandaloneSpec:
[info] standalone
[info] - sample D test
[info] Run completed in 108 milliseconds.
[info] Total number of tests run: 1
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 1, failed 0, canceled 0, ignored 0, pending 0
[info] All tests passed.
```

With testOnly/-z selecting one test from a root spec
----------------------------------------------------

``` shell
$ sbt testOnly *RootSpec -- -z "root sample"
[info] RootSpec:
[info] root
[info] - root sample
[info] NestedSpecA:
[info] nested A
[info] NestedSpecB:
[info] nested B
[info] Run completed in 144 milliseconds.
[info] Total number of tests run: 1
[info] Suites: completed 3, aborted 0
[info] Tests: succeeded 1, failed 0, canceled 0, ignored 0, pending 0
[info] All tests passed.
```

With testOnly/-z selecting one test from a nested spec
------------------------------------------------------

I would expect one test to be executed but zero were executed.

``` shell
$ sbt testOnly *RootSpec -- -z "sample A test"
[info] NestedSpecA:
[info] nested A
[info] NestedSpecB:
[info] nested B
[info] Run completed in 135 milliseconds.
[info] Total number of tests run: 0
[info] Suites: completed 3, aborted 0
[info] Tests: succeeded 0, failed 0, canceled 0, ignored 0, pending 0
[info] No tests were executed.
```

This is expected because of the constructor parameter but just to be thorough, here is the output if I select the the nested class specifically:

``` shell
$ sbt testOnly *NestedSpecA -- -z "sample A test"
[info] NestedSpecA:
[info] nested A
[info] NestedSpecB:
[info] nested B
[info] Run completed in 135 milliseconds.
[info] Total number of tests run: 0
[info] Suites: completed 3, aborted 0
[info] Tests: succeeded 0, failed 0, canceled 0, ignored 0, pending 0
[info] No tests were executed.
```
