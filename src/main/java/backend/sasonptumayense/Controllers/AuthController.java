package backend.sasonptumayense.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import backend.sasonptumayense.request.LoginRequest;
import backend.sasonptumayense.request.RegisterRequest;
import backend.sasonptumayense.request.SelectedQuestionRequest;
import backend.sasonptumayense.response.ApiResponse;
import backend.sasonptumayense.response.AuthResponse;
import backend.sasonptumayense.response.DynamicResponseErrors;
import backend.sasonptumayense.service.AuthService;
import backend.sasonptumayense.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class AuthController {
	private final AuthService authService;
	private final UserService userService;
	private final SelectedQuestionController selectedQuestionController;
	
	@PostMapping(value = "login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(authService.login(request));
	}
	
	@PostMapping(value = "register")
	public ResponseEntity<ApiResponse> register(@ModelAttribute RegisterRequest request, @RequestParam("photo") MultipartFile photo) {
	//public ResponseEntity<String> register(@ModelAttribute RegisterRequest request, @RequestParam("photo") MultipartFile photo) {
		DynamicResponseErrors obj = new DynamicResponseErrors();
		
		String name = request.getName();
		if(name == null || name.isEmpty()) {
			obj.addError("name", "Name is required");
		}

		String lastName = request.getLastName();
		if(lastName == null || lastName.isEmpty()) {
			obj.addError("lastName", "Last name is required");
		}

		String identificationTypeId = request.getIdentificationTypeId();
		if(identificationTypeId == null || identificationTypeId.isEmpty()) {
			obj.addError("identificationTypeId", "Identification type is required");
		}

		String identificationNumber = request.getIdentificationNumber();
		if(identificationNumber == null || identificationNumber.isEmpty()) {
			obj.addError("identificationNumber", "Identification number is required");
		}

		if(userService.getUserByIdentificationNumber(identificationNumber) != null) {
			obj.addError("identificationNumber", "Identification number already exists");
		}

		String email = request.getEmail();
		if(email == null || email.isEmpty()) {
			obj.addError("email", "Email is required");
		}

		String phone = request.getPhone();
		if(phone == null || phone.isEmpty()) {
			obj.addError("phone", "Phone is required");
		}

		String genderId = request.getGenderId();
		if(genderId == null || genderId.isEmpty()) {
			obj.addError("genderId", "Gender is required");
		}

		String username = request.getUsername();
		if(username == null || username.isEmpty()) {
			obj.addError("username", "Username is required");
		}
		if(userService.getUserByUsername(username) != null) {
			obj.addError("username", "Username already exists");
		}

		String password = request.getPassword();
		if(password == null || password.isEmpty()) {
			obj.addError("password", "Password is required");
		}

		String firstQuestion = request.getFirstQuestion();
		if(firstQuestion == null || firstQuestion.isEmpty()) {
			obj.addError("firstQuestion", "First question is required");
		}

		String firstAnswer = request.getFirstAnswer();
		if(firstAnswer == null || firstAnswer.isEmpty()) {
			obj.addError("firstAnswer", "First answer is required");
		}

		String secondQuestion = request.getSecondQuestion();
		if(secondQuestion == null || secondQuestion.isEmpty()) {
			obj.addError("secondQuestion", "Second question is required");
		}

		String secondAnswer = request.getSecondAnswer();
		if(secondAnswer == null || secondAnswer.isEmpty()) {
			obj.addError("secondAnswer", "Second answer is required");
		}

		if(obj.hasErrors()) {
			return new ResponseEntity<ApiResponse>(
				new ApiResponse(HttpStatus.BAD_REQUEST, "Validation error", obj.getErrors()), HttpStatus.BAD_REQUEST);
		}else{
			AuthResponse response = authService.register(request, photo);
			System.out.println("Response: " + response);

			selectedQuestionController.saveSelectedQuestion(new SelectedQuestionRequest(
				response.getUser().getId(),
				Integer.parseInt(request.getFirstQuestion()),
				request.getFirstAnswer()
			));

			selectedQuestionController.saveSelectedQuestion(new SelectedQuestionRequest(
				response.getUser().getId(),
				Integer.parseInt(request.getSecondQuestion()),
				request.getSecondAnswer()
			));

			return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "Saved", response), HttpStatus.OK);

		}
	}
}

