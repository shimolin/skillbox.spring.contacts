При запуске программа выводит синтаксис всех доступных команд (PRINT, ADD, DELETE, SAVE, EXIT) и ожидает ввод от пользователя. Команды допускается вводить на любом регистре.

Если в application.properties указан профиль spring.profiles.active=init, то программа загружает список пользователей из файла указанного в переменной app.contactsfilename

При вводе команды SAVE программа сохраняет файл в папку указанную в переменной app.pathtosave

В переменных app.fullNameRegex, app.phoneRegex, app.emailRegex и app.filenameRegex хранятся соответствующие регулярные выражения, на случай необходимости корректировки
