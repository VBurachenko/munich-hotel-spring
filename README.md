# munich-hotel

## System online-booking of apartments in the hotel.

### Task description:
   Customer registers in system and authorize by email and password. After that he fill the search form, and get a list of free and available rooms. Authorized customer choose the room (or rooms),  booking it ( or them), and system automatically generates  the invoice. Client can pay this invoice instantly, or in hotel during arriving. Booking can be canceled, or payed his invoice by customer in case it was not payed, otherwise customer contacts with support and solve this questions.
   Admin registers in system as an usual customer at first and than became an admin by moderator. Admin able to spectate full lists of registered users, bookings, invoices and rooms. Customer can be found  by userDto, email, or telephone number, and blocking/unblocked by admin. Booking can be found by booking-id, and process it in order of booking-status and invoice of booking. Booking can be canceled by admin. Also admin can add new roomsDto, found them by number, make able/disable them, change their description. Hotel-room can be finaly deleted from the system only after canceling all bookingsDto with this room.
   Moder is predetermined "superadmin" witch able to doing everything as same as usual admin, including the possibility of registering new one admin.

### Technical description of the project:
Build tool: [Maven](http://maven.apache.org/); <br>
Java: [8](https://javaee.github.io/javaee-spec/javadocs/); <br>
General architecture of application corresponds patterns: MVC and Layered architecture; <br>