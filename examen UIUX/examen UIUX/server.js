import express from "express"
import mongoose from "mongoose"
import User from "./user.js"

const app = express()
const port = 9090
const hostname = "127.0.0.1"

mongoose.connect("mongodb://127.0.0.1:27017/examen")
    .then(() => console.log('connected'))
    .catch(() => console.log('fail'))

app.use(express.json()) // Middleware to parse JSON bodies

app.post("/user/login", (req, res) => {
    const { email, password } = req.body;

    User.findOne({ email, password })
        .then((user) => {
            if (user) {
                res.status(200).json(user);
            } else {
                res.status(404).json({ message: "User not found" });
            }
        })
        .catch((err) => res.status(500).json(err));
});

app.listen(port, hostname, () => {
    console.log(`Server running at http://${hostname}:${port}`);

    // Create a default user for testing
    User.create({ email: "admin", password: "admin" })
        .then((user) => console.log('User created:', user))
        .catch((err) => console.log('Failed to create user:', err));
});
