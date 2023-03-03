package microdev.vaadinspringcomercial.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import microdev.vaadinspringcomercial.model.ClientModel;
import microdev.vaadinspringcomercial.model.ProdTem;
import microdev.vaadinspringcomercial.repository.ProdTemRepository;

import java.util.Objects;


public class FacturacionView extends Div {

    private ClientModel selectedClient;
    private ProdTemRepository prodTemRepository;

    public FacturacionView(ProdTemRepository prodTemRepository) {
        this.prodTemRepository = prodTemRepository;
        selectedClient = new ClientModel();
        addTopComponent();
    }

    private void addTopComponent() {
        Button addClientDataButton = new Button("Buscar Cliente");
        addClientDataButton.addClickListener(e -> addClientDataButtonEvent());

        NumberField totalNumberField = new NumberField();
        totalNumberField.getStyle().set("color", "#DC143C");
        totalNumberField.getStyle().set("font-size", "150%");
        totalNumberField.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
        totalNumberField.setPrefixComponent(new Label("$"));
        // TODO: Bring the value from a controller package class.
        totalNumberField.setValue(123.321);



        FormLayout formLayout = new FormLayout();
        formLayout.add(addClientDataButton, totalNumberField);

        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("500px", 2));

        this.add(formLayout);
    }

    private void addClientDataButtonEvent() {
        // TODO Refactor this method to make it shorter!
        Dialog clientSearchDialog = new Dialog();
        clientSearchDialog.setHeaderTitle("Buscar un cliente");

        TextField direccionTextField = new TextField("Direccion");
        TextField telefonoTextField = new TextField("Telefono");
        EmailField emailEmailField = new EmailField("Correo:");
        emailEmailField.setErrorMessage("Correo invalido");

        Button enviarConsumidorFinalButton = new Button("Consumidor Final");
        Button enviarClienteButton = new Button("Seleccionar Cliente");
        enviarClienteButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        enviarClienteButton.addClickListener(e ->
                enviarClienteAction(direccionTextField.getValue(), telefonoTextField.getValue(), emailEmailField,
                        clientSearchDialog));
        enviarConsumidorFinalButton.addClickListener(e -> {
            enviarConsumidorFinalAction();
            clientSearchDialog.close();
        });

        FormLayout datosClienteFormLayout = new FormLayout();

        ComboBox<ClientModel>[] comboBoxes = this.generateComboboxForDialog(emailEmailField, direccionTextField, telefonoTextField);

        datosClienteFormLayout.add(comboBoxes[0], comboBoxes[1], direccionTextField, telefonoTextField,
                emailEmailField, enviarClienteButton, enviarConsumidorFinalButton);

        clientSearchDialog.add(datosClienteFormLayout);
        clientSearchDialog.open();
    }

    private ComboBox<ClientModel>[] generateComboboxForDialog(EmailField emailEmailField, TextField direccionTextField,
                                                              TextField telefonoTextField) {
        ComboBox<ClientModel> cedulaClienteComboBox = new ComboBox<>("Cedula/RUC:");
        // TODO: Remove dummy data for real data
        cedulaClienteComboBox.setItems(ClientModel.getDummyData());
        cedulaClienteComboBox.setItemLabelGenerator(ClientModel::getId);
        cedulaClienteComboBox.setPlaceholder("Ingrese el ID del cliente");
        cedulaClienteComboBox.setAllowCustomValue(true);

        ComboBox<ClientModel> nombreClienteComboBox = new ComboBox<>("Nombre:");
        ComboBox.ItemFilter<ClientModel> nombreItemFilter = (client, filterString)
                -> client.getNombre().toLowerCase().contains(filterString.toLowerCase());
        // TODO: Dummy data
        nombreClienteComboBox.setItems(nombreItemFilter, ClientModel.getDummyData());
        nombreClienteComboBox.setItemLabelGenerator(ClientModel::getNombre);
        nombreClienteComboBox.setPlaceholder("Ingrese el nombre del cliente");
        nombreClienteComboBox.setAllowCustomValue(true);

        cedulaClienteComboBox.addValueChangeListener(e -> {
            this.selectedClient = e.getValue();
            this.showClientData(nombreClienteComboBox, emailEmailField, direccionTextField, telefonoTextField);
        });
        cedulaClienteComboBox.addCustomValueSetListener(e -> this.selectedClient.setId(e.getDetail()));
        nombreClienteComboBox.addValueChangeListener(e -> {
            this.selectedClient = e.getValue();
            this.showClientData(cedulaClienteComboBox, emailEmailField, direccionTextField, telefonoTextField);
        });
        nombreClienteComboBox.addCustomValueSetListener(e -> this.selectedClient.setNombre(e.getDetail()));

        return new ComboBox[]{cedulaClienteComboBox, nombreClienteComboBox};
    }

    private void showClientData(ComboBox<ClientModel> comboBox, EmailField emailField, TextField... textFields) {
        comboBox.setValue(this.selectedClient);
        emailField.setValue(this.selectedClient.getEmail());
        textFields[0].setValue(this.selectedClient.getDireccion());
        textFields[1].setValue(this.selectedClient.getTelefono());
    }

    private void enviarClienteAction(String direccion, String telefono, EmailField email, Dialog parent) {
        if (Objects.nonNull(this.selectedClient.getId()) && Objects.nonNull(this.selectedClient.getNombre())) {
            if (!direccion.isBlank() && !telefono.isBlank() && (!email.isInvalid() && !email.getValue().isBlank())) {
                this.selectedClient.setDireccion(direccion);
                this.selectedClient.setTelefono(telefono);
                this.selectedClient.setEmail(email.getValue());
                this.showNotification("Se ha cargado el cliente con el nombre: " + this.selectedClient.getNombre(), false);
                parent.close();
            } else {
                this.showNotification("Revise que los campos direccion, telefono o email esten correctamente ingresados", true);
            }
        } else {
            this.showNotification("Ingrese el RUC y el Nombre correctamente", true);
        }
    }

    private void showNotification(String text, boolean isError) {
        Notification notification = new Notification(text,
                5000, Notification.Position.TOP_CENTER);
        notification.addThemeVariants(isError ? NotificationVariant.LUMO_ERROR : NotificationVariant.LUMO_SUCCESS);
        notification.open();
    }

    private void enviarConsumidorFinalAction() {
        this.showNotification("Consumidor Final!", false);
    }

}
