package com.danasoftware.jenkins.pipeline.libraries

public void cloneSourceCode(def targetUrl, def targetDir, def credentialsID, def branchName = "origin/master", def scmClass = "GitSCM"){
  return {
    node {
      try {
        println "Starting clone for ${targetDir} at " + new Date()
        checkout scm: [
                 $class: "${scmClass}",
                 branches: [[name: "${branchName}"]],
                 userRemoteConfigs: [[credentialsId: "${credentialsID}", url: "${targetUrl}"]],
                 extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: "${targetDir}"]]
                 ]
        println "Finished clone for ${targetDir} at " + new Date()
      } catch (Exception e){
        throw e
      }
    }
  }
}
