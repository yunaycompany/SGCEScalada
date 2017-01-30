Ext.define('SGCEscalada.view.canton.EliminarCanton', {
    extend: 'Ext.window.Window',
    alias: 'widget.eliminarCanton',
    autoShow: true,
    title: 'Eliminar cant&oacute;n',
    resizable: false,
    modal: true,
    layout: 'fit',
    width:309,

    initComponent: function () {
        var me = this;

        me.cbxEliminarCanton = Ext.widget('cbxCantones',{
            labelAlign:'top'
        });

        me.fsDatosAdicionarCanton = Ext.create('Ext.form.FieldSet', {
            title: 'Datos cant&oacute;n:',
            layout: 'fit',
            padding: 5,
            margin: 5,
            items: [me.cbxEliminarCanton]
        });

        me.btnAceptarAdicionarCanton = Ext.create('Ext.button.Button', {
            formBind: true,
            text: 'Eliminar',
            action: 'eliminarCanton'
        });

        me.btnCancelarAdicionarCanton = Ext.create('Ext.button.Button', {
            text: 'Cancelar',
            action: 'cancelarEliminarCanton'
        });

        me.frmAdicionarCanton= Ext.create('Ext.form.Panel', {
            //padding: 5,
            border: 0,
            items: [me.fsDatosAdicionarCanton],
            buttons: [me.btnCancelarAdicionarCanton, me.btnAceptarAdicionarCanton]
        });


        me.items = [me.frmAdicionarCanton];
        me.callParent(arguments);
    }
});
