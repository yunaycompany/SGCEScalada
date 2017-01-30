Ext.define('SGCEscalada.view.media.WndSubirVideo', {
    extend: 'Ext.window.Window',
    alias: 'widget.wndSubirVideo',
    title: 'Subir video',
    resizable: false,
    autoShow: true,
    modal: true,
    width: 500,
    layout: 'fit',
    border: 1,

    initComponent: function () {
        var me = this;

        /*me.cbxCompetenciasSubirImagen = Ext.widget('cbxCompetencias', {
            labelAlign: 'top'
        });

        me.fsNombreCompetenciaSubirImagen = Ext.create('Ext.form.FieldSet', {
            title: 'Nombre competencia:',
            layout: 'fit',
            padding: 5,
            margin: 5,
            items: [me.cbxCompetenciasSubirImagen]
        });
*/
        me.flSubirVideo = Ext.widget('cargarVideo');

        me.fsCargarVideoSubir = Ext.create('Ext.form.FieldSet', {
            title: 'Cargar video:',
            layout: 'fit',
            padding: 5,
            margin: 5,
            items: [me.flSubirVideo]
        });

        me.btnAceptarCargarVideoSubir = Ext.create('Ext.button.Button', {
           formBind: true,
           text: 'Aceptar',
           action: 'aceptarCargarVideoSubir'
       });

       me.btnCancelarCargarVideoSubir = Ext.create('Ext.button.Button', {
           text: 'Cancelar',
           action: 'cancelarCargarVideoSubir'
       });

        me.frmCargarVideo = Ext.create('Ext.form.Panel',{
            border: 0,
            items: [/*me.fsNombreCompetenciaSubirVideo,*/me.fsCargarVideoSubir],
            buttons: [me.btnCancelarCargarVideoSubir,me.btnAceptarCargarVideoSubir]
        });

        me.items = [me.frmCargarVideo];
        me.callParent(arguments);
    }
});