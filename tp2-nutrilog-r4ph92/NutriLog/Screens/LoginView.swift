import LocalAuthentication
import SwiftUI

struct LoginView: View {
    var onLoginTap: () -> Void
    
    var body: some View {
        ZStack {
            Color.loginBackground
            VStack {
                Image("nutrilog-logo")
                    .resizable()
                    .scaledToFit()
                
                Button {
                    onLoginTap()
                } label: {
                    Image(systemName: "faceid")
                    Text("Se connecter")
                }
                .buttonStyle(.plain)
                .frame(maxWidth: 150, maxHeight: 40)
                .background(Color.loginButton)
                .foregroundStyle(.white)
                .cornerRadius(8)
            }
        }
        .ignoresSafeArea()
    }
}

#Preview {
    LoginView(onLoginTap: {})
}

