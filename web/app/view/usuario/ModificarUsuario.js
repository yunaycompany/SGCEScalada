Ext.apply(Ext.form.field.VTypes, {
    password: function(val, field) {
        if (field.initialPassField) {
            var pwd = field.up('form').down('#' + field.initialPassField);
            return (val == pwd.getValue());
        }
        return true;
    },
    passwordText: 'No coinciden las contraseñas.'
});
Ext.define('SGCEscalada.view.usuario.ModificarUsuario', {
    extend: 'Ext.window.Window',
    alias: 'widget.ModificarUsuario',
    autoShow: true,
    title: 'Modificar Usuario',
    resizable: false,
    modal: true,
    width: 288,
    layout: 'fit',

    initComponent: function () {
        this.items = [
            {
                xtype: 'form',
                border: 0,
                //padding: 5,
                items: [
                    {
                        xtype: 'textfield',
                        name:'idUsuario',
                        id:'idUsuario',
                        hidden:true,
                        padding: 5,
                        margin: 5,
                        value:1
                    },
                    {
                        xtype: 'textfield',
                        name: 'Usuario',
                        fieldLabel: 'Usuario',
                        enable: false,
                        value: 'nom',
                        id:'txfUsuario',
                        padding: 5,
                        margin: 5,
                        hidden:true
                    },
                    {
                        xtype: 'textfield',
                        inputType: 'password',
                        name: 'Contrasenna',
                        fieldLabel: 'Contrase&ntildea',
                        id:'txfContrasenna',
                        allowBlank:false,
                        blankText:'Este campo es requerido.',
                        initialPassField: 'txfConfirmar',
                        padding: 5,
                        margin: 5
//                        vtype: 'password'
                    },
                    {
                        xtype: 'textfield',
                        inputType: 'password',
                        name: 'Confirmar',
                        fieldLabel: 'Confirmar',
                        id:'txfConfirmar',
                        allowBlank:false,
                        blankText:'Este campo es requerido.',
                        initialPassField: 'txfContrasenna',
                        padding: 5,
                        margin: 5,
                        vtype: 'password'
                    },
                    {
                        xtype: 'combobox',
                        name: 'roll',
                        fieldLabel: 'Rol',
                        queryMode: 'local',
                        editable: false,
                        padding: 5,
                        margin: 5,
                        store: Ext.create('Ext.data.Store', {
                            fields: ["idRol", "rol"],
                            data: [
                                {idRol: 1, rol: "Administrador"},
                                {idRol: 2, rol: "Jurado"},
                                {idRol: 3, rol: "Cliente"}]
                        }),
                        displayField: "rol",
                        valueField: "idRol",
                        id:'cbxRol',
                        allowBlank:false,
                        blankText:'Este campo es requerido.'
                    }

                ],
                buttons: [
                    {
                        text: 'Cancelar',
                        handler: function () {
                            this.up('window').close();
                        }
                    },
                    {
                        text: 'Modificar',
                        action: 'Modificar',
                        formBind:true
                    }
                ]
            }
        ],
            this.callParent(arguments)
    }
});