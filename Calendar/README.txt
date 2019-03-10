Name: 		Pengbin Li
JDK Used: 	JAVA 1.8.0_191
IDE Used: 		Eclipse Mars.2 Release (4.5.2)
Main File: 		CalendarFrame
Load File: 		If you run the demo with eclipse, you do not need to move any file to other place. It reads SCULOGO.jpg
		to show the logo. It reads Days.dat to generate history events.
		If you want to run .class file under bin folder. It reads SCULOGO.jpg and Days.dat under \bin folder.
		Use command 'java ui.CalendarFrame' under \bin folder.
		If you want to compile yourself, you need to use command 'javac -encoding utf8 *.java' under \src\ui folder.
		Then use command 'java ui.CalendarFrame' under \src folder. It reads SCULOGO.jpg and Days.dat under 
		\src folder.
Other: 		The project was coded on a Chinese version Windows. Text file encoding is UTF-8.
		Click calendar grid that shows in the frame can change selected date to show the events that user added
		on the day user clicked. Clicking also overrides eventDate that prepared to add new events. When clicking
		AddEvents button, it overrides the selected date. The date format is MM-dd-yyyy, it must be dash here,
		slash is not implemented. Changing color function has two strategies. One is changing all added events'
		color and later added events' color (All). The other one is only changing next serial added events' color, not
		change existed events' color (Next).
