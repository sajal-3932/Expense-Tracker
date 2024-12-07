<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <link rel="stylesheet" href="Signuppage.css">
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar">
        <div class="logo">
            <a href="index.jsp" class="logo-text">Expense Tracker</a>
        </div>
    </nav>

    <!-- Sign Up Form -->
    <div class="signup-container">
        <h2>Sign Up</h2>

        <!-- The form submits to the /sign_up servlet (POST method) -->
        <form action="sign_up" method="POST" class="signup-form">
            <label for="name">Username</label>
            <input type="text" id="username" name="name" required>

            <label for="email">Email</label>
            <input type="email" id="email" name="email" required>

            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>

            <label for="confirmPassword">Confirm Password</label>
            <input type="password" id="confirm-password" name="confirmPassword" required>

            <button type="submit" class="signup-btn">Sign Up</button>
        </form>

        <p>Already have an account? <a href="login.jsp">Login here</a></p>
    </div>
</body>
</html>
