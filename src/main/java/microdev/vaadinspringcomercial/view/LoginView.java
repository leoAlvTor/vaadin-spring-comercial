package microdev.vaadinspringcomercial.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinSession;
import microdev.vaadinspringcomercial.controller.UserController;
import microdev.vaadinspringcomercial.model.UserModel;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Route("")
@RouteAlias("login")
@PageTitle("Login")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private final LoginForm login = new LoginForm();
    private final UserController userController;
    private final VaadinSession vaadinSession;

    public LoginView(UserController userController){
        vaadinSession = VaadinSession.getCurrent();
        this.userController = userController;

        addComponents();
        addEvents();
    }

    private void addComponents(){
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        add(new H1("Bienvenido, inicie sesión para continuar"), login);
    }

    private void addEvents(){
        login.addLoginListener(e->{
            Optional<UserModel> optionalUserModel = userController.loginUser(e.getUsername(), e.getPassword());
            if(optionalUserModel.isPresent()){
                vaadinSession.setAttribute("user", optionalUserModel.get());
                UI.getCurrent().access(() -> UI.getCurrent().navigate("main_view"));
            }else{
                UI.getCurrent().getPage().reload();
            }
        });
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        Map<String, List<String>> parameters = beforeEnterEvent.getLocation().getQueryParameters().getParameters();
        if(parameters.containsKey("deny")) {
            Notification notification = Notification.show(parameters.get("deny").get(0), 5000, Notification.Position.TOP_START);
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        } else if (parameters.containsKey("error")) {
            login.setError(true);
        }
    }
}
