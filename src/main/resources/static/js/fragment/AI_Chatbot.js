//챗봇

const chatbotToggler = document.querySelector(".chatbot-toggler");
const chatIcons = document.querySelectorAll("#chat-icon");
const chatBlock = document.querySelector(".chatBlock");

const closeBtn = document.querySelector(".close-btn");
const outputbox = document.querySelector(".outputbox");
const chatInput = document.querySelector(".inputbox textarea");
const sendChatBtn = document.querySelector(".inputbox span");

chatbotToggler.addEventListener("click", function(){
    chatBlock.classList.toggle('on');
    chatIcons.forEach(chatIcon => {
        chatIcon.classList.toggle('on');
    });
});

closeBtn.addEventListener("click", () => document.body.classList.remove("on"));


let userMessage = null; // Variable to store user's message
const inputInitHeight = chatInput.scrollHeight;

const createChatLi = (message, className) => {
    // Create a chat <li> element with passed message and className
    const chatLi = document.createElement("li");
    const classes = className.split(' ');
    chatLi.classList.add("chat", ...classes);
    let chatContent = className === "outgoing" ? `<p></p>` : `<span class="material-symbols-outlined">smart_toy</span><p></p>`;
    chatLi.innerHTML = chatContent;
    chatLi.querySelector("p").textContent = message;
    return chatLi; // return chat <li> element
}


const handleChat = () => {
    userMessage = chatInput.value.trim(); // Get user entered message and remove extra whitespace
    if (!userMessage) return;

    // Clear the input textarea and set its height to default
    chatInput.value = "";
    chatInput.style.height = `${inputInitHeight}px`;

    // Append the user's message to the outputbox
    outputbox.appendChild(createChatLi(userMessage, "outgoing"));
    outputbox.scrollTo(0, outputbox.scrollHeight);

    // Display "Thinking..." message while waiting for the response
    const incomingChatLi = createChatLi("...", "incoming");

    outputbox.appendChild(incomingChatLi);
    outputbox.scrollTo(0, outputbox.scrollHeight);

    // AJAX Request
    $.ajax({
        url: '/AI/Chatbot/',  // 요청을 보낼 URL입니다.
        type: 'GET',
        data: {
            'user_input': userMessage
        },
        success: function (data) {
            if (data.message === 'SUCCESS') {
                incomingChatLi.querySelector("p").textContent = data.result;
            }
        },
        error: function (request, status, error) {
            console.log('Error fetching data:', error);
        }
    });
}

// chatInput.addEventListener("input", () => {
//     // Adjust the height of the input textarea based on its content
//     chatInput.style.height = `${chatInput.scrollHeight}px`;
// });

chatInput.addEventListener("keydown", (e) => {
    // If Enter key is pressed without Shift key and the window 
    // width is greater than 800px, handle the chat
    if (e.key === "Enter" && !e.shiftKey && window.innerWidth > 800) {
        e.preventDefault();
        handleChat();
    }
});

sendChatBtn.addEventListener("click", handleChat);
