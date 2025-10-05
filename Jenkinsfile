@Library('jenkins-shared-library') _
buildAndNotify(
  repoUrl: 'https://github.com/example/maven-app.git',
  branch: 'develop',
  mavenCommand: 'clean package',
  notifyType: 'slack',
  slackChannel: '#ci-alerts'
)
