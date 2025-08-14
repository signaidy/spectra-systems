import jenkins.model.*
import hudson.security.*

def instance = Jenkins.getInstanceOrNull()
if (instance == null) {
  return
}

def adminUser = System.getenv("JENKINS_ADMIN_ID") ?: "admin"
def adminPass = System.getenv("JENKINS_ADMIN_PASSWORD") ?: "admin123"

def hudsonRealm = new HudsonPrivateSecurityRealm(false)
if (hudsonRealm.getUser(adminUser) == null) {
  hudsonRealm.createAccount(adminUser, adminPass)
}
instance.setSecurityRealm(hudsonRealm)

def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(false)
instance.setAuthorizationStrategy(strategy)

instance.save()
println "Jenkins admin user ensured: ${adminUser}"