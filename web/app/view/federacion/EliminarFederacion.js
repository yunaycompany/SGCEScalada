Ext.define('SGCEscalada.view.federacion.EliminarFederacion', {
    extend: 'Ext.window.Window',
    alias: 'widget.eliminarFederacion',
    autoShow: true,
    title: 'Eliminar federaci&oacute;n',
    resizable: false,
    modal: true,
    width:309,
    layout: 'fit',

    initComponent: function () {
        var me = this;

        me.cbxEliminarFederacion = Ext.widget('cbxFederaciones');

        me.fsDatosAdicionarFederacion = Ext.create('Ext.form.FieldSet', {
            title: 'Datos federaci&oacute;n:',
            layout: 'fit',
            padding: 5,
            margin: 5,
            items: [me.cbxEliminarFederacion]
        });

        me.btnAceptarAdicionarFederacion = Ext.create('Ext.button.Button', {
            formBind: true,
            text: 'Eliminar',
            action: 'eliminarFederacion'
        });

        me.btnCancelarAdicionarFederacion = Ext.create('Ext.button.Button', {
            text: 'Cancelar',
            action: 'cancelarEliminarFederacion'
        });

        me.frmAdicionarFederacion = Ext.create('Ext.form.Panel', {
            //padding: 5,
            border: 0,
            items: [me.fsDatosAdicionarFederacion],
            buttons: [me.btnCancelarAdicionarFederacion, me.btnAceptarAdicionarFederacion]
        });


        me.items = [me.frmAdicionarFederacion];
        me.callParent(arguments)
    }
});

