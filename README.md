java-abntest-platform
=====================

just a simple java platform to perform common operation for abn testing.

this is an maven project imported into eclipse. if you don't use maven you have to download all dependencies by hand and adjust the classpath for your needs.

for maven users, be sure that m2eclipse plugin is istalled for eclipse integration (dependecies and builds).

the platform have an interface, called ExperimentManager to perform common experiments actions. at this very moment, the only implementation is local implementation.

for the future, the plan is to integrate Google Analytics Content Experiments platform. 