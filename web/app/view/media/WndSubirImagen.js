Ext.define('SGCEscalada.view.media.WndSubirImagen', {
    extend: 'Ext.window.Window',
    alias: 'widget.wndSubirImagen',
    title: 'Subir imagen',
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
        me.flSubirImagen = Ext.widget('cargarImagen');

        me.fsCargarImagenSubir = Ext.create('Ext.form.FieldSet', {
            title: 'Cargar imagen:',
            layout: 'fit',
            padding: 5,
            margin: 5,
            items: [me.flSubirImagen]
        });

        me.btnAceptarCargarImagenSubir = Ext.create('Ext.button.Button', {
           formBind: true,
           text: 'Aceptar',
           action: 'aceptarCargarImagenSubir'                 
       });

       me.btnCancelarCargarImagenSubir = Ext.create('Ext.button.Button', {
           text: 'Cancelar',
           action: 'cancelarCargarImagenSubir'
       });
         me.nombre = Ext.create('Ext.form.field.Text',{
            name: 'nombre',
            labelAlign:'top',
            allowBlank: false,
            blankText: 'Este campo es requerido.',
            layout:'fit',
             padding: 5,
//            margin: 5,
            fieldLabel: 'Nombre de la Imagen'
        });
        me.fecha = Ext.create('Ext.form.field.Date', {
            name: 'fecha',
            labelAlign: 'top',
            allowBlank: false,
            blankText: 'Este campo es requerido.',
            editable:false,            
            layout:'fit',
            padding: 5,
            fieldLabel: 'Fecha de la Imagen',
            maxValue: new Date()
        });
       

        me.frmCargarImagen = Ext.create('Ext.form.Panel',{
            border: 0,  layout:'column',            
            items: [/*me.fsNombreCompetenciaSubirImagen,*/me.fsCargarImagenSubir,me.nombre, me.fecha],
            buttons: [me.btnCancelarCargarImagenSubir,me.btnAceptarCargarImagenSubir]
        });

        me.items = [me.frmCargarImagen];
        me.callParent(arguments);
    }
});