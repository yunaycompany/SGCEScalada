Ext.define('SGCEscalada.view.direccion.FsDireccion',{
    extend:'Ext.form.FieldSet',
    alias: 'widget.fsDireccion',
    title:'Direcci&oacute;n:',
    margin: 5,
    padding: 5,

    initComponent: function () {
        var me = this;

        me.cbxProvinciaDireccion = Ext.widget('cbxProvincias', {
            action: 'direccionProvincia'
        });

        me.cbxCantonDireccion = Ext.widget('cbxCantones', {
            action: 'direccionCanton',
            disabled: true
        });
 me.cbxCiudadDireccion = Ext.widget('cbxCiudad', {
            action: 'direccionCiudad',
            disabled: true
        });
        me.cbxParroquiaDireccion = Ext.widget('cbxParroquias', {
            action: 'direccionParroquia',
            disabled: true
        });

        me.items = [me.cbxProvinciaDireccion, me.cbxCantonDireccion, me.cbxCiudadDireccion,me.cbxParroquiaDireccion];
        me.callParent(arguments);
    }
});