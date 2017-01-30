Ext.define('SGCEscalada.view.ciudad.AdicionarCiudad', {
    extend: 'Ext.window.Window',
    alias: 'widget.adicionarCiudad',
    autoShow: true,
    title: 'Adicionar Ciudad',
    resizable: false,
    modal: true,
    width: 309,
    layout: 'fit',

    initComponent: function () {
        var me = this;

        me.cbxProvinciaAdicionarCiudad = Ext.widget('cbxProvincias', {
            action: 'adicionarCiudadProvincia'
        });

        me.cbxCantonAdicionarCiudad = Ext.widget('cbxCantones', {
            action: 'adicionarCiudadCanton',
            disabled: true
        });

        me.ftNombreCiudad = Ext.create('Ext.form.field.Text', {
            fieldLabel: 'Nombre',
            allowBlank: false,
            name:'nombreCiudad',
            padding: 5,
            margin: 5,
            blankText: 'Este campo es requerido.'
        });

        me.fsDatosAdicionarCiudad = Ext.create('Ext.form.FieldSet', {
            title: 'Datos Ciudad:',
            padding: 5,
            margin: 5,
            items: [me.cbxProvinciaAdicionarCiudad, me.cbxCantonAdicionarCiudad, me.ftNombreCiudad]
        });

        me.btnAceptarAdicionarCiudad = Ext.create('Ext.button.Button', {
            formBind: true,
            text: 'Aceptar',
            action: 'aceptarAdicionarCiudad'
        });

        me.btnCancelarAdicionarCiudad = Ext.create('Ext.button.Button', {
            text: 'Cancelar',
            action: 'cancelarAdicionarCiudad'
        });

        me.frmAdicionarCiudad = Ext.create('Ext.form.Panel', {            //
            border: 0,
            items: [me.fsDatosAdicionarCiudad],
            buttons: [me.btnCancelarAdicionarCiudad, me.btnAceptarAdicionarCiudad]
        });


        me.items = [me.frmAdicionarCiudad];
        me.callParent(arguments);
    }
});