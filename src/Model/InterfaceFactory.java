package Model;


public class InterfaceFactory
{
    // this method maps the ProductDAO interface
    // to the appropriate data storage mechanism
    public static ModelInterface getModelDAO(String SelectedMake)
    {
        ModelInterface cDAO = new ModelSelection(SelectedMake);
        return cDAO;
    }
   /* public static SetupInterface getSetupDAO()
    {
        SetupInterface sDAO = new SetupShopInfo();
        return sDAO;
    }
    public static PrinterInterface getPrintInt()
    {
        PrinterInterface printInt = new print();
        return printInt;
    }
    public static SavedInspectionsInterface getInspectionsDAO()
    {
        SavedInspectionsInterface savedIns = new SavedInspections();
        return savedIns;
    }
    public static saveFileInterface getSaveFileDAO()
    {
        saveFileInterface saveFile = new saveFile();
        return saveFile;
    }
    public static openFileInterface getOpenFileDAO()
    {
        openFileInterface openFile = new openFile();
        return openFile;
    }
   */
}