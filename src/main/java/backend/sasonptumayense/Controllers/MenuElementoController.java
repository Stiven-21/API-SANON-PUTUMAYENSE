package backend.sasonptumayense.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import backend.sasonptumayense.model.MenuElemento;
import backend.sasonptumayense.request.MenuElementoRequest;
import backend.sasonptumayense.response.ApiResponse;
import backend.sasonptumayense.response.DynamicResponseErrors;
import backend.sasonptumayense.service.ElementosMenuService;
import backend.sasonptumayense.service.MenuElementoService;
import backend.sasonptumayense.service.MenusService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MenuElementoController {
    private final MenuElementoService menuElementoService;
    private final MenusService menusService;
    private final ElementosMenuService elementosMenuService;

    public ResponseEntity<ApiResponse> getAllMenuElementos(){
        return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "OK", menuElementoService.getAllMenuElementos()), HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> createMenuElemento(MenuElementoRequest request){
        DynamicResponseErrors obj = new DynamicResponseErrors();

        if(request == null) obj.addError("request", "Request is required");

        if(request.getMenuId() == null || request.getMenuId().isEmpty()) obj.addError("menuId", "MenuId is required");
        if(request.getElementoMenuId() == null || request.getElementoMenuId().isEmpty()) obj.addError("elementoMenuId", "ElementoMenuId is required");

        if(obj.hasErrors()) return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, "An error has occurred", obj.getErrors()), HttpStatus.BAD_REQUEST);

        if(menusService.getMenusById(Integer.parseInt(request.getMenuId())) == null) return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, "Menu not found", null), HttpStatus.BAD_REQUEST);
        if(elementosMenuService.getById(Integer.parseInt(request.getElementoMenuId())) == null) return new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.BAD_REQUEST, "ElementosMenu not found", null), HttpStatus.BAD_REQUEST);

        MenuElemento menuElemento = menuElementoService.createMenuElemento(request);
        
        return (menuElemento != null) ? 
            new ResponseEntity<ApiResponse>(new ApiResponse(HttpStatus.OK, "Saved", menuElemento), HttpStatus.OK) 
            : new ResponseEntity<>(new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An error has occurred", null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
