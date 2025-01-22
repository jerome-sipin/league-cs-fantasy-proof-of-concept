**Introduction**
_____
This project is a proof-of-concept for a web application for playing fantasy esports (similar to fantasy football/basketball) for the professional scene for the video games "Counter-Strike 2" and "League of Legends". For simplicity's sake, this version features only Counter-Strike teams and players, as the scene is more international compared to League's regional circuits. 

So far, basic logic for creating fantasy teams and adding players has been implemented, with further features (such as integration with external APIs for gathering player/team data). 

This project was made with no commercial intent and simply as a study / starting point into creating a private web application to be used amongst myself and my friends to play fantasy League and Counter-Strike. 


**Technologies Used**
____
**Frontend** - HTML/JSP | Bootstrap | CSS | JavaScript 

**Backend** - Java | Spring Boot 

**Database** - MySQL

**Build / Deployment** - Tomcat | Maven

**Features**
______
**Login / Registration** 
- Users are able to register. There are validations to ensure that emails and usernames are unique. 
- Passwords are encrypted 

**User Roles / Authorization** 
- There are currently two roles - Admin and User. 
- The Admin role has authority to create new (real) teams and players. 
- The User role has authority to create new (fantasy) teams and add players to their team. 
- Guests may view the site, but cannot create fantasy teams. 


**Search** 
- Users may use a text search to find other users, fantasy teams, real teams, and players. In the future, there will be a single, unified search form for all of these tables, but as of now, they are within separate pages. 

**Fantasy Team Creation / Editing** 
- Users may only have 1 fantasy team as of this version. By clicking the "Your Team" button on the navigation bar, you are taken to the screen to create a fantasy team or to view your fantasy team. 
- When creating a fantasy team, there is validation on whether your team name is unique. 
- Currently, adding/removing players to a fantasy team is similar to a shopping cart of an e-commerce platform. When you add a player into the team, you are redirected to the view screen for your team. 

**Create Real Team / Players**
- The Admin may create new entries for real teams and players. There is validation on the uniqueness of player names and team names. Images are URLs from Counter-Strike news website/forum HLTV. 


**Screenshots**
____
![View User Team](/Screenshots/view_user_team.png)


**Future Plans**
____
- Further fantasy team editing logic
	- Ability to rename team
	- Ability to edit team lineup without changing views/refreshing the page
- Search bar for searching through all tables. 
- Currently there is no way to change a user's authorities inside the application. Future versions should have a functionality for the Admin to be able to edit user roles. 
- Real team and players should be updated by an external API, rather than manually by the Admin through create forms. 



**Lessons Learned**
____



