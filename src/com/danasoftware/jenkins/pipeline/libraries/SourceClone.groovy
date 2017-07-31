package com.danasoftware.jenkins.pipeline.libraries

public void cloneSourceCode(def targetNode, def targetUrl, def targetDir, def credentialsID, def branchName = "origin/master", def scmClass = "GitSCM"){
  if (branchName=="" || branchName==null || branchName=="null") {
    branchName="origin/master"
  }

  return {
    node ("${targetNode}") {
      try {
        println "Starting clone for ${targetDir}:${branchName} at " + new Date()
        checkout scm: [
                 $class: "${scmClass}",
                 branches: [[name: "${branchName}"]],
                 userRemoteConfigs: [[credentialsId: "${credentialsID}", url: "${targetUrl}"]],
                 extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: "${targetDir}"]]
                 ]
        println "Finished clone for ${targetDir}:${branchName} at " + new Date()
      } catch (Exception e){
        throw e
      }
    }
  }
}
