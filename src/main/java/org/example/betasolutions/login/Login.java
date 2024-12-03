package org.example.betasolutions.login;

    public class Login {
        private String username; // mangler vi username i vores ER model?
        private String password;

        // Default constructor
        public Login() {}

        // Parameterized constructor
        public Login(String username, String password) {
            this.username = username;
            this.password = password;
        }

        // Getters and setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }


