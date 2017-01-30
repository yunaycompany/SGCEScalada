Ext.define('SGCEscalada.view.provincia.EliminarProvincia', {
    extend: 'Ext.window.Window',
    alias: 'widget.eliminarProvincia',
    autoShow: true,
    title: 'Eliminar Provincia',
    resizable: false,
    modal: true,
    width: 309,
    layout: 'fit',

    initComponent: function () {
        var me = this;

        me.cbxEliminarProvincia = Ext.widget('cbxProvincias');

        me.fsDatosAdicionarProvincia = Ext.create('Ext.form.FieldSet',{
            title: 'Datos provincia:',
            layout: 'fit',
            padding: 5,
            margin: 5,
            items:[me.cbxEliminarProvincia]
        });

        me.btnAceptarAdicionarProvincia = Ext.create('Ext.button.Button',{
            formBind:true,
            text: 'Eliminar',
            action:'eliminarProvincia'
        });

        me.btnCancelarAdicionarProvincia = Ext.create('Ext.button.Button',{
            text: 'Cancelar',
            action:'cancelarEliminarProvincia'
        });

        me.frmAdicionarProvincia = Ext.create('Ext.form.Panel',{
            //padding:5,
            border:0,
            items:[me.fsDatosAdicionarProvincia],
            buttons:[me.btnCancelarAdicionarProvincia, me.btnAceptarAdicionarProvincia]
        });


        me.items = [me.frmAdicionarProvincia];
        me.callParent(arguments);
    }
});