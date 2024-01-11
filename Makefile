test:
	 mvn clean test -DENV="$(ENV)" -DGIT_COMMIT="$(GIT_COMMIT)" -DGIT_BRANCH="$(GIT_BRANCH)" -DBUILD_NUMBER="$(BUILD_NUMBER)" -DCOMPONENT_ID="$(COMPONENT_ID)" -Dmaven.test.failure.ignore=false
