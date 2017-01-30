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

Ext.define('SGCEscalada.view.usuario.AdicionarUsuario', {
    extend: 'Ext.window.Window',
    alias: 'widget.adicionarUsuario',
    autoShow: true,
    title: 'Adicionar Usuario',
    resizable: false,
    modal: true,
    width: 288,
    layout: 'fit',
    border: 1,

    initComponent: function () {
        this.items = [
            {
                xtype: 'form',
                border: 0,
                //padding:5,
                items: [
                    {
                        xtype: 'textfield',
                        name: 'Usuario',
                        allowBlank:false,
                        fieldLabel: 'Usuario',
                        padding: 5,
                        margin: 5,
                        blankText:'Este campo es requerido.'

                    },
                    {
                        xtype: 'textfield',
                        inputType: 'password',
                        name: 'Contrasenna',
                        allowBlank:false,
                        id:'pass1',
                        fieldLabel: 'Contrase&ntilde;a',
                        initialPassField: 'pass2',
                        vtype: 'password',
                        padding: 5,
                        margin: 5,
                        blankText:'Este campo es requerido.'
                    },
                    {
                        xtype: 'textfield',
                        inputType: 'password',
                        name: 'Confirmar',
                        allowBlank:false,
                        fieldLabel: 'Confirmar',
                        initialPassField: 'pass1',
                        id:'pass2',
                        vtype: 'password',
                        padding: 5,
                        margin: 5,
                        blankText:'Este campo es requerido.'
                    },
                    {
                        xtype: 'combobox',
                        name: 'roll',
                        fieldLabel: 'Rol',
                        queryMode: 'local',
                        allowBlank:false,
                        editable: false,
                        padding: 5,
                        margin: 5,
                        store:Ext.create('Ext.data.Store',{
                            fields:["idRol","rol"],
                            data: [
                                {idRol:1,rol:"Administrador"},
                                {idRol:2,rol:"Jurado"},
                                {idRol:3,rol:"Cliente"}]
                        }) ,
                        displayField: "rol",
                        valueField: "idRol",
                        blankText:'Este campo es requerido.'
                    }

                ],
                buttons: [
                    {
                        text: 'Cancelar',
                        handler: function(){
                            this.up('window').close();
                        }
                    },
                    {
                        text: 'Aceptar',
                        action: 'Aceptar',
                        formBind:true
                    }
                ]
            }
        ],
            this.callParent(arguments)
    }
});