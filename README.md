# simple-actions
Prove out simple actions


# CI is done
* possibly one thing to do is demonstrate ENV variable for the `junit_password` that is accessible to the workflow action AND to the Java via maven properties or similar



## CD todo 

We should be able to incorporate CD in the same file where we match the deploy only on pushing of tags but this reliees on somebody pushing the corect 'next' version which is prone to error




## ***NB the following really needs links for clarification***

## Things we would like
* a CHANGELOG which includes all PRs since last release
* a simple mechanism for progressing through versions 
  * should also have the option for bumping `MAJOR` and `MINOR`, maybe this could be a manually triggered workflow action that prompts for values
  * default is to keep bumping the `PATCH` value


## Questions
* do we need Sonatype keys for each Merge user ?
* can we ban direct pushes to `main` as in PRs are the only mechanism
  * Yes, using Branch protection Rule/Ruleset `Require a pull request before merging`
  * Do we want this ? Most likely Yes but what does this mean regarding pushing tags for the release workflow0
* Can we fix `could not change directory to "/home/runner/work/simple-actions/simple-actions": Permission denied` ?


## Things to remember
* For mvn clean Deploy use `-DskipTests`

