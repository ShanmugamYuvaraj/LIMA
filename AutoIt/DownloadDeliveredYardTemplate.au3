WinWait("[TITLE:Opening ; CLASS:MozillaDialogClass]","", 10)

If WinExists("[TITLE:Opening ; CLASS:MozillaDialogClass]") Then

WinActivate("[TITLE:Opening ; CLASS:MozillaDialogClass]")

Send("{DOWN}")

Sleep(10)

Send("{TAB}")

Sleep(10)

Send("{TAB}")

Sleep(10)

Send("{ENTER}")
EndIf


