job('NodeJS Docker example') {
    scm {
        git('git://github.com/DevoloperNour/docker-cicd/edit/master/basics/dsl/nodejs_docker.groovy','master') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@devophift.work')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
   
    
    steps {
        dockerBuildAndPublish {
            repositoryName('DevoloperNour/docker-cicd')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('bshara.eng@gmail.com')
            buildContext('./basics/')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}

