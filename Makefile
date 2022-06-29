build-generators:
	fvm flutter pub run build_runner build --delete-conflicting-outputs

CC=dart
CC_UI=fvm flutter


default: fmt dep
	@echo "Please run \"make server !&\" to run the server"


dep:
	$(CC_UI) pub get


fmt:
	$(CC_UI) format .
	$(CC_UI) analyze .

clean:
	$(CC_UI) clean