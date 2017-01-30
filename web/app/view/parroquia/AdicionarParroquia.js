Ext.define('SGCEscalada.view.parroquia.AdicionarParroquia', {
    extend: 'Ext.window.Window',
    alias: 'widget.adicionarParroquia',
    autoShow: true,
    title: 'Adicionar Parroquia',
    resizable: false,
    modal: true,
    width: 309,
    layout: 'fit',
    initComponent: function() {
        var me = this;

        me.cbxProvinciaAdicionarParroquia = Ext.widget('cbxProvincias', {
            action: 'adicionarParroquiaProvincia'
        });

        me.cbxCantonAdicionarParroquia = Ext.widget('cbxCantones', {
            action: 'adicionarParroquiaCanton',
            disabled: true
        });

        me.cbxCiudadAdicionarParroquia = Ext.widget('cbxCiudad', {
            action: 'adicionarParroquiaCiudad',
            disabled: true
        });
        me.ftNombreParroquia = Ext.create('Ext.form.field.Text', {
            fieldLabel: 'Nombre',
            allowBlank: false,
            name: 'nombreParroquia',
            padding: 5,
            margin: 5,
            blankText: 'Este campo es requerido.'
        });

        me.fsDatosAdicionarParroquia = Ext.create('Ext.form.FieldSet', {
            title: 'Datos parroquia:',
            padding: 5,
            margin: 5,
            items: [me.cbxProvinciaAdicionarParroquia, me.cbxCantonAdicionarParroquia, me.cbxCiudadAdicionarParroquia, me.ftNombreParroquia]
        });

        me.btnAceptarAdicionarParroquia = Ext.create('Ext.button.Button', {
            formBind: true,
            text: 'Aceptar',
            action: 'aceptarAdicionarParroquia'
        });

        me.btnCancelarAdicionarParroquia = Ext.create('Ext.button.Button', {
            text: 'Cancelar',
            action: 'cancelarAdicionarParroquia'
        });

        me.frmAdicionarParroquia = Ext.create('Ext.form.Panel', {//
            border: 0,
            items: [me.fsDatosAdicionarParroquia],
            buttons: [me.btnCancelarAdicionarParroquia, me.btnAceptarAdicionarParroquia]
        });


        me.items = [me.frmAdicionarParroquia];
        me.callParent(arguments);
    }
});