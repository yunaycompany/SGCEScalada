Ext.define('SGCEscalada.view.loggin.Loggin',{
    extend: 'Ext.window.Window',
    alias: 'widget.loggin',
    autoShow: true,
    title: 'Autenticaci&oacute;n',
    resizable: false,
    modal: true,
    //width: 309,
    layout: 'fit',
    closable: false,
    draggable: false,

    initComponent: function () {
        var me = this;

        me.ftUsuario = Ext.create('Ext.form.field.Text', {
            fieldLabel: 'Usuario',
            allowBlank: false,
            name: 'username',
            blankText: 'Este campo es requerido.',
            padding: 5,
            margin: 5
        });

        me.ftUsuarioClave = Ext.create('Ext.form.field.Text', {
            fieldLabel: 'Clave',
            allowBlank: false,
            name: 'password',
            inputType: 'password',
            blankText: 'Este campo es requerido.',
            padding: 5,
            margin: 5
        });

        me.fsDatosLoggin = Ext.create('Ext.form.FieldSet', {
            title: 'Datos usuario:',
            padding: 5,
            margin: 5,
            items: [me.ftUsuario, me.ftUsuarioClave]
        });

        me.btnAceptarLoggin = Ext.create('Ext.button.Button', {
            formBind: true,
            text: 'Aceptar',
            action: 'aceptarLoggin'
        });

        me.frmLoggin = Ext.create('Ext.form.Panel', {
            //padding:5,
            border: 0,
            items: [me.fsDatosLoggin],
            buttons: [me.btnAceptarLoggin]
        });


        me.items = [me.frmLoggin];
        me.callParent(arguments);
    }
});