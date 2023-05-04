
[Setup]
AppId={{Main}}
AppName=IntegrationProject
AppVersion=1.0
AppVerName=IntegrationProject 1.0
AppPublisher=Eletra Energy
AppComments=ProjectIntegration
DefaultDirName={localappdata}\IntegrationProject
DisableStartupPrompt=Yes
DisableDirPage=Yes
DisableProgramGroupPage=Yes
DisableReadyPage=Yes
DisableFinishedPage=Yes
DisableWelcomePage=Yes
DefaultGroupName=Eletra Energy
AllowNoIcons=yes
MinVersion=0,5.1
OutputBaseFilename=IntegrationProject
Compression=lzma
SolidCompression=yes
PrivilegesRequired=lowest
SetupIconFile=IntegrationProject\IntegrationProject.ico
UninstallDisplayIcon={app}\IntegrationProject.ico
UninstallDisplayName=IntegrationProject
WizardSmallImageFile=IntegrationProject-setup-icon.bmp 
WizardImageStretch=yes 
ArchitecturesInstallIn64BitMode=x64
[Languages]
Name: "brazilianportuguese"; MessagesFile: "compiler:Languages\BrazilianPortuguese.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked

[Files]
Source: "IntegrationProject\IntegrationProject.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "IntegrationProject\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs

[Icons]
Name: "{group}\IntegrationProject"; Filename: "{app}\IntegrationProject.exe"; IconFilename: "{app}\IntegrationProject.ico"; Check: returnTrue()
Name: "{commondesktop}\IntegrationProject"; Filename: "{app}\IntegrationProject.exe";  IconFilename: "{app}\IntegrationProject.ico"; Check: returnFalse()
Name: "{commondesktop}\IntegrationProject"; Filename: "{app}\IntegrationProject"; Tasks: desktopicon

[Run]
Filename: "{app}\IntegrationProject.exe"; Parameters: "-Xappcds:generatecache"; Check: returnFalse()
Filename: "{app}\IntegrationProject.exe"; Description: "{cm:LaunchProgram,IntegrationProject}"; Flags: nowait postinstall skipifsilent; Check: returnTrue()
Filename: "{app}\IntegrationProject.exe"; Parameters: "-install -svcName ""IntegrationProject"" -svcDesc ""IntegrationProject"" -mainExe ""IntegrationProject.exe""  "; Check: returnFalse()

[UninstallRun]
Filename: "{app}\IntegrationProject.exe "; Parameters: "-uninstall -svcName IntegrationProject -stopOnUninstall"; Check: returnFalse()

[Code]
function returnTrue(): Boolean;
begin
  Result := True;
end;
function returnFalse(): Boolean;
begin
  Result := False;
end;
function InitializeSetup(): Boolean;
begin
// Possible future improvements:
//   if version less or same => just launch app
//   if upgrade => check if same app is running and wait for it to exit
//   Add pack200/unpack200 support? 
  Result := True;
end;  
