# CICD-1 Coding Test — Review, Improve and Reflect

**Student ID: G00461762**  
**Examination group:** B (OpenFeign):  
**Original examination pull-request URL (`exam-work`): https://github.com/Beatricepapi/G00461762/pull/1?utm_source=chatgpt.com**  
**New review repository URL: https://github.com/Beatricepapi/CICD1_Review_G00461762_Final.git**

## 1. My original examination self-assessment

Review the code **as submitted in the original examination**. Do not change the original repository, branch or pull request. Check your own Group A or Group B examination paper and award marks only for evidence present in the original submission.

### Task 1 — /40
**My mark: 8 / 40**  
**Evidence from the original code (files, methods, working or missing requirements):
In my original examination submission, I created some Java classes and attempted to use a DTO and validation. However, I implemented Student and Room instead of the required Booking model. I did not correctly implement all the required booking fields, including organiser name, attendee count, room ID and ACTIVE status. The response DTO was also incomplete. There was a syntax error in my entity relationship annotation, which could prevent compilation.**

### Task 2 — /40
**My mark: 2 / 40**  
**Evidence from the original code: I attempted to create controller and service classes, but I did not implement the required POST /api/bookings endpoint. My original code did not include a working OpenFeign client to retrieve room information from the separate Room Service. I also did not implement the room-capacity validation or complete the booking persistence logic. Some service methods returned null.**

### Task 3 — /20
**My mark: 0 / 20**  
**Evidence from the original code: I did not implement the required GET /api/bookings/{id} endpoint. My original submission did not retrieve a booking from the local database and return the required response DTO. I also did not implement the appropriate not-found response.**

**Total self-assessed original examination mark: 10 / 100**

**Checkpoint:** Commit this completed self-assessment as the **first review commit in your new repository**, before you modify or add application code. The lecturer decides the official original examination mark.

## 2. Corrections and improvements in my new project

Start from a **fresh copy of the starter template for your own exam group**, in a **new GitHub repository**. You may consult or selectively reuse your own original examination code, but the new project must demonstrate that you understand and can correct it. Do not change your original examination submission.

Describe **two or three important problems** in your original code. For each, explain what was wrong, which class or method you changed in the new project, and why the corrected approach works.

**Problem and correction 1: In my original examination, I created Student and Room classes instead of implementing the required Booking model. My entity also contained an incorrect @JoinColumn annotation. In the new project, I created Booking.java with the correct fields: ID, organiser name, attendee count, room ID and ACTIVE status. I also created CreateBookingRequest.java and BookingResponse.java to separate incoming requests from API responses.**

**Problem and correction 2: My original code did not contain a working OpenFeign client or room-capacity validation. In the new project, I created RoomClient.java to communicate with the Room Service on port 8082. I added RoomResponse.java and implemented createBooking() in BookingService.java. This method retrieves the selected room, checks its capacity and saves the booking only when the attendee count is allowed.**

**Problem and correction 3 (optional): My original submission did not correctly implement booking creation or retrieval. Some service methods returned null. In the new project, I created BookingRepository.java using Spring Data JPA and added BookingController.java. The POST endpoint returns HTTP 201 for a successful booking, while the GET endpoint retrieves bookings from H2 and returns HTTP 404 when a booking does not exist.**

## 3. API testing and evidence

Use Swagger UI or another HTTP client. For **at least four meaningful checks**, record the request/input, expected result, actual HTTP status/response, and pass/fail. Include unsuccessful cases; a running application alone is not enough.

**Test 1 — successful booking request:**
- Request / input: POST /api/bookings with {"roomId":1,"organiserName":"Aoife Byrne","attendeeCount":18}
- Expected: HTTP 201 Created, with a booking response containing an ID and ACTIVE status.
- Actual HTTP status / body: HTTP 201 Created. Response: {"id":1,"organiserName":"Aoife Byrne","attendeeCount":18,"roomId":1,"status":"ACTIVE"}
- Pass / fail: Pass

**Test 2 — invalid input or excessive attendee count:**
- Request / input: POST /api/bookings with {"roomId":2,"organiserName":"John Murphy","attendeeCount":20}. Room 2 has a capacity of 10.
- Expected: HTTP 400 Bad Request because 20 attendees exceed the room capacity.
- Actual HTTP status / body: HTTP 400 Bad Request. The response contained "status":400 and "error":"Bad Request".
- Pass / fail: Pass

**Test 3 — missing booking or room:**
- Request / input: GET /api/bookings/9999
- Expected: HTTP 404 Not Found because booking 9999 does not exist.
- Actual HTTP status / body: HTTP 404 Not Found. The response contained "status":404 and "error":"Not Found".
- Pass / fail: Pass

**Test 4 — requirement specific to my group:**
- Request / input: GET /api/bookings/1
- Expected: HTTP 200 OK with the previously saved booking returned from the local Booking database.
- Actual HTTP status / body: HTTP 200 OK. Response: {"id":1,"organiserName":"Aoife Byrne","attendeeCount":18,"roomId":1,"status":"ACTIVE"}
- Pass / fail: Pass

**Group B:** Run the **provided** Room Service separately on port 8082 and the **new** Booking Service on port 8081. Demonstrate that booking creation obtains room details through OpenFeign and that booking retrieval uses the local Booking database. Do not modify the Room Service.

**Group B demonstration: I ran the provided Room Service separately on port 8082 and the new Booking Service on port 8081. The Room Service returned room capacities of 24, 10 and 40. My Booking Service used OpenFeign to retrieve room information when creating bookings. The successful booking and over-capacity rejection demonstrated that room capacity was checked. Retrieving booking 1 demonstrated that saved bookings could be read from the local Booking database. I did not modify the provided Room Service.**

## 4. Reflection

**What did I understand during the examination but struggle to implement?**

**During the examination, I understood that a Spring Boot application needed models, repositories, services and controllers, but I struggled to connect them correctly. I also found it difficult to translate the Booking Service requirements into working code within the time limit. My original submission did not implement the required booking endpoints or OpenFeign communication.**

**Which Spring Boot, JPA, validation, JPQL or OpenFeign concept became clearer after correcting my code? Give a specific example.**

**OpenFeign became much clearer to me. I learned that the Booking Service can use RoomClient.java to request room information from a separate service without storing room details in its own database. For example, I tested a booking for 20 attendees in a room with capacity 10, and my Booking Service correctly returned HTTP 400. I also learned how Spring Data JPA saves and retrieves bookings using save() and findById().**

**What will I do differently in my next coding test or API development task?**

**Next time, I will read the requirements carefully before starting and identify the required classes, fields and endpoints. I will build and test one part at a time instead of writing everything without checking it. I will also test successful and unsuccessful requests in Swagger UI, check compilation errors early and leave enough time to commit and submit my work.**

## Review lab assessment — 20 marks

- Honest original self-assessment with code evidence: **4 marks**
- Explanation of mistakes and Spring Boot concepts: **6 marks**
- Corrections in the new application: **4 marks**
- API testing and demonstration of understanding: **4 marks**
- Reflection and future improvement: **2 marks**

**Total: 20 marks.** Simply submitting a working solution does not demonstrate the learning required by this review.

## Proposed mark recovery — only if confirmed for CICD-1

If the same approach as OOP is adopted, the review could recover **up to 30% of marks lost** in the original examination, scaled by the review-lab mark.

**Marks recovered = (100 − official original exam mark) × 0.30 × (review lab mark ÷ 20)**

Example: exam **40/100** and review **16/20** → **14.4 recovered marks**, revised exam **54.4/100**. This is **not an approved CICD-1 policy unless confirmed by the lecturer**. The original official exam mark—not your self-awarded grade—is used in the calculation.

## Submission

Commit and push the **new review repository** with the completed README, corrected Spring Boot code and API-test evidence. Submit its GitHub URL on Moodle. **Do not push any changes to the original examination repository or `exam-work` pull request.**