# Prompt Demo Controller - Spring AI Example

This code implements a Spring Boot controller for demonstrating various functionalities of the Spring AI Chat API. It allows users to interact with a large language model (LLM) through different prompt configurations.

**Key Features:**

* **Simple Prompts:** Handles basic prompts where the user provides an input value and receives a single fact in response.
* **User Prompts:** Enables users to submit their own text as prompts for the LLM.
* **User-System Prompts:** Combines user prompts with system instructions, allowing more control over the LLM's behavior.
* **User-Assistant Prompts:** Introduces an assistant role to the conversation. The assistant can be instructed to perform specific tasks, such as grammar correction.
* **Resource-Based Prompts:** Demonstrates loading prompts from external resources (e.g., files) for reusability.

**Functionality Breakdown:**

* **Controllers:**
    * `PromptDemoController`: Handles incoming requests and interacts with the ChatClient to send prompts to the LLM.
* **API Endpoints:**
    * `/simplePrompt/{inputVal}`: Accepts a user input value and returns a single fact about it.
    * `/simpleUserPrompt`: Allows users to submit their own text prompts.
    * `/simpleUserSystemPrompt`: Combines user prompts with system instructions.
    * `/simpleUserAssistantPrompt`: Introduces an assistant role for grammar correction.
    * `/simpleUserAssistantResPrompt`: Demonstrates loading prompts from external resources.

**Overall, this code provides a starting point for exploring and experimenting with Spring AI's prompt-based interaction with LLMs.**

**Note:** This is a basic overview. Refer to the code for specific implementation details.