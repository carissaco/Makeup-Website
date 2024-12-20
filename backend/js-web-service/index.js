
const express = require('express');
const OpenAI = require("openai")
require('dotenv').config();

const app = express();
const PORT = 3000;

// Middleware to parse JSON requests
app.use(express.json());

const openai = new OpenAI({
    apiKey: process.env.OPENAI_API_KEY,
  });


// Define a route to handle OpenAI calls
app.post('/api/chat', async (req, res) => {
    const { message } = req.body;

    if (!message) {
        return res.status(400).json({ error: 'Message is required' });
    }

    try {
        // Call OpenAI API
        // Example: using the chat completion
        const chatResponse = await openai.chat.completions.create({
            model: "gpt-3.5-turbo",
            messages: [
                { role: 'system', content: 'You are a helpful assistant.' },
                { role: 'user', content: message }
            ],
        });
        console.log(chatResponse.choices[0].message.content);
       

        // Send OpenAI response back to the client
        res.json({
            response: JSON.parse(chatResponse.choices[0].message.content),
        });
    } catch (error) {
        console.error(error.response ? error.response.data : error.message);
        res.status(500).json({ error: 'An error occurred while communicating with OpenAI' });
    }
});

// Define a route
app.get('/', (req, res) => {
    res.send('Welcome to my web service!');
});

app.get('/api/data', (req, res) => {
    console.log (req.query)
    const responseData = {
        message: 'Hello, this is your JSON response!',
        success: true,
        data: {
            id: 1,
            name: 'John Doe',
            email: 'johndoe@example.com',
            roles: ['admin', 'user']
        }
    };

    // Send JSON response
    res.json(responseData);
});

app.post('/api/data/post', (req, res) => {
    // Access parameters from the JSON body
    const { name, age, email } = req.body;

    // Send a response
    res.json({
        success: true,
        message: 'Data received successfully',
        data: { name, age, email }
    });
});

// Start the server
app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});
