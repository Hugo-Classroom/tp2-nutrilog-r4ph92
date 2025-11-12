import LocalAuthentication
import SwiftUI

struct RootView: View {
    // TODO: Cette partie sera vue en classe pour l'expliquer comme il faut
    @State private var isAuthenticated = true
    @State private var showLogin = false
    
    var body: some View {
        ZStack {
            if isAuthenticated {
                HomeView()
            } else {
                if showLogin {
                    LoginView(onLoginTap: authenticate)
                } else {
                    ProgressView("Chargement...")
                        .onAppear {
                            DispatchQueue.main.asyncAfter(deadline: .now() + 0.5) {
                                showLogin = true
                                authenticate()
                            }
                        }
                }
            }
        }
        .animation(.easeInOut, value: isAuthenticated)
    }
    
    func authenticate() {
        let context = LAContext()
        var error: NSError?
        
        if context.canEvaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, error: &error) {
            let reason = "We need to unlock to see the app"
            
            context.evaluatePolicy(.deviceOwnerAuthenticationWithBiometrics, localizedReason: reason) { success, authenticationError in
                DispatchQueue.main.async {
                    if success {
                        isAuthenticated = true
                    } else {
                        print("Echec FaceId")
                    }
                }
            }
        } else {
            print("Face ID non disponible : \(error?.localizedDescription ?? "inconnue")")
        }
    }
}

#Preview {
    RootView()
}

