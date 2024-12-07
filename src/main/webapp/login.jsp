<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign In</title>
    <link rel="stylesheet" href="Signinpage.css">
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar">
        <div class="logo">
            <a href="index.jsp" class="logo-text">Expense Tracker</a>
        </div>
    </nav>

    <!-- Sign In Form -->
    <div class="signin-container">
        <h2>Sign In</h2>

        <!-- The form submits to the /login servlet (POST method) -->
        <form action="login" method="POST" class="signin-form">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" required>

            <label for="password">Password</label>
            <input type="password" id="password" name="password" required>

            <button type="submit" class="signin-btn">Sign In</button>
        </form>
        
        <p>Don't have an account? <a href="sign_up.jsp">Sign Up here</a></p>
    </div>
</body>
</html>
