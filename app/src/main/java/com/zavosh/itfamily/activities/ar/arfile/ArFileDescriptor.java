package com.zavosh.itfamily.activities.ar.arfile;

import android.content.Context;
import android.util.Log;

import androidx.core.util.Pair;
import androidx.core.util.Supplier;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Stream;

public class ArFileDescriptor {

    // There is only one database file, with no version updates
    public ArFileRefImageDatabase refImageDatabase = new ArFileRefImageDatabase();

    // There is only one ar scenarios file
    public ArFileScenario scenario = new ArFileScenario();


    public List<ArFileRefImage> refImages= new ArrayList<>();

    public List<ArFilePhoto> photos= new ArrayList<>();
    public List<ArFileVideo> videos= new ArrayList<>();
    public List<ArFileModel3D> models3D= new ArrayList<>();
    public List<ArFileSound> sounds= new ArrayList<>();

    private Context context;
    private File arDirectory;

    private ArCandidateFiles candidateFiles = new ArCandidateFiles();

    public class ArCandidateFiles {
        public List<ArFileRefImageDatabase> refImageDatabase = new ArrayList<>();
        public List<ArFileScenario> scenarios = new ArrayList<>();
        public List<ArFileRefImage> refImages = new ArrayList<>();
        public List<ArFilePhoto> photos = new ArrayList<>();
        public List<ArFileVideo> videos = new ArrayList<>();
        public List<ArFileModel3D> models3D = new ArrayList<>();
        public List<ArFileSound> sounds = new ArrayList<>();
    }

    public ArFileDescriptor(Context ctx) {

        this.context = ctx;
        this.arDirectory = new File(context.getFilesDir() + "/" + "ar");
    }


    public void siftProcessArFiles() {


        // Step 1 - Fill Ar Files

        File[] files = arDirectory.listFiles();

        if (files != null && files.length > 0) {

            for (int i = 0; i < files.length; i++) {

                String filename = files[i].getName().toLowerCase();
                filename = filename.substring(0, filename.lastIndexOf(".")).trim();

                Log.d("json", "Json Step3.1 file-strip:" + filename);
                Log.d("json", "Json Step3.2 files-count:" + files.length);

                if (filename.contains("ar_typescenario")) {

                    // ar_typescenario_uv1

                    ArFileScenario f = new ArFileScenario();
                    f.file = files[i];
                    f.fileType = ArFile.ArFileType.Scenario;

                    f.uv = getARFilePartValueInt(filename,"uv");


                    this.candidateFiles.scenarios.add(f);



                } else if (filename.contains("ar_typedb")) {

                    // ar_typedb

                    ArFileRefImageDatabase f = new ArFileRefImageDatabase();
                    f.file = files[i];
                    f.fileType = ArFile.ArFileType.RefImageDatabase;

                    this.candidateFiles.refImageDatabase.add(f);


                    Log.d("json", "json reference image database:" + f.file.getName());


                } else if (filename.contains("ar_typeref")) {

                    ArFileRefImage f = new ArFileRefImage();
                    f.file = files[i];
                    f.fileType = ArFile.ArFileType.ReferenceImage;

                    // ar_typeref_mag1 _refnum1_iscover0_uv1

                    f.mag = getARFilePartValueInt(filename, "mag");
                    f.refnum = getARFilePartValueInt(filename, "refnum");
                    f.isCover = getARFilePartValueBool(filename, "iscover");
                    f.uv = getARFilePartValueInt(filename, "uv");

                    this.candidateFiles.refImages.add(f);


                } else if (filename.contains("ar_typephoto")) {

                    ArFilePhoto f = new ArFilePhoto();
                    f.file = files[i];
                    f.fileType = ArFile.ArFileType.Photo;

                    // ar_typephoto_mag1 _refnum1_index0_uv1

                    f.mag = getARFilePartValueInt(filename, "mag");
                    f.refnum = getARFilePartValueInt(filename, "refnum");
                    f.index = getARFilePartValueInt(filename, "index");
                    f.uv = getARFilePartValueInt(filename, "uv");

                    this.candidateFiles.photos.add(f);


                } else if (filename.contains("ar_typevideo")) {

                    ArFileVideo f = new ArFileVideo();
                    f.file = files[i];
                    f.fileType = ArFile.ArFileType.Video;

                    // ar_typevideo_mag1 _refnum2_index0_uv1

                    f.mag = getARFilePartValueInt(filename, "mag");
                    f.refnum = getARFilePartValueInt(filename, "refnum");
                    f.index = getARFilePartValueInt(filename, "index");
                    f.uv = getARFilePartValueInt(filename, "uv");

                    this.candidateFiles.videos.add(f);


                } else if (filename.contains("ar_type3d")) {

                    ArFileModel3D f = new ArFileModel3D();
                    f.file = files[i];
                    f.fileType = ArFile.ArFileType.Model3D;

                    // ar_type3d_mag1_refnum3 _index1_uv1

                    f.mag = getARFilePartValueInt(filename, "mag");
                    f.refnum = getARFilePartValueInt(filename, "refnum");
                    f.index = getARFilePartValueInt(filename, "index");
                    f.uv = getARFilePartValueInt(filename, "uv");

                    this.candidateFiles.models3D.add(f);


                } else if (filename.contains("ar_typesound")) {

                    ArFileSound f = new ArFileSound();
                    f.file = files[i];
                    f.fileType = ArFile.ArFileType.Sound;

                    // ar_typesound_mag1_refnum3_index1_uv1

                    f.mag = getARFilePartValueInt(filename, "mag");
                    f.refnum = getARFilePartValueInt(filename, "refnum");
                    f.index = getARFilePartValueInt(filename, "index");
                    f.uv = getARFilePartValueInt(filename, "uv");

                    this.candidateFiles.sounds.add(f);

                }


            }
        }


        // Step 2 - Sifting Higher UVs (Update Version) and filling in descriptors


        // There is only one database which is going to be filled with reference images at runtime
        if(candidateFiles.refImageDatabase.size() > 0)
            this.refImageDatabase = candidateFiles.refImageDatabase.get(0);


        // ArFileScenario:---------------------------

        {

            // ar_typescenario_uv1

            Supplier<Stream<ArFileScenario>> streamSupplier = () -> this.candidateFiles.scenarios.stream();


            streamSupplier.get().max((val1, val2) -> {
                return ((Integer)val1.uv).compareTo(val2.uv);
            }).ifPresent( p -> {
                this.scenario = p;
            });

            Log.d("Stream", " Stream Scenario : "+ this.scenario.file.getName());
        }

        // ---------------------------


        // Notes
        // only refnum must match (refnum must always be unique unless an update to a specific refnum is required)
        // two different refnums indicate two different reference images and therefore their update versions must not be checked



        // ArFileRefImage:---------------------------

        {

            // ar_typeref_mag1_refnum1_iscover0_uv1

            SortedSet<Integer> processedRefNums = new TreeSet<>();

            Supplier<Stream<ArFileRefImage>> streamSupplier = () -> this.candidateFiles.refImages.stream();


            for (ArFileRefImage item: this.candidateFiles.refImages ) {

                streamSupplier.get().filter(f ->{
                    return f.refnum == item.refnum;
                }).max((val1, val2) -> {
                    return ((Integer)val1.uv).compareTo(val2.uv);
                }).ifPresent( p -> {
                    if(processedRefNums.contains(p.refnum)==false)
                    {
                        processedRefNums.add(p.refnum);
                        this.refImages.add(p);
                    }
                });
            }

            Log.d("Stream", " Stream refImages count: "+ this.refImages.size());
            for (ArFileRefImage a: this.refImages
                 ) {

                Log.d("Stream", " Stream refImages : "+ a.file.getName());
            }
        }

        // ---------------------------

        // ArFilePhoto:---------------------------

        {

            // Since we can have more than one photo for a specific scenarios, then
            // the 'refnum' and the 'index' must be checked to check for the update
            // version of a file.
            // There can be muliple files (updates) for a specific index of the refnum.

            // ar_typephoto_mag1_refnum1_index0_uv1

            //SortedSet<Integer> processedRefNums = new TreeSet<>();
            //SparseIntArray processedIndicesOfRefnumMap = new SparseIntArray(); // key:refnum, value:index
            //SortedSet<Map.Entry<Integer, Integer>> processedRefNums = new TreeSet<>(); // key:refnum, value:index
            //Map<Integer, Integer> processedEntries = new HashMap<>(); // key:refnum, value:index
            List<Pair<Integer, Integer>> processedEntries = new ArrayList<>();

            Supplier<Stream<ArFilePhoto>> streamSupplier = () -> this.candidateFiles.photos.stream();


            for (ArFilePhoto item: this.candidateFiles.photos ) {

                streamSupplier.get().filter(f ->{
                    return f.refnum == item.refnum && f.index == item.index ;
                }).max((val1, val2) -> {
                    return ((Integer)val1.uv).compareTo(val2.uv);
                }).ifPresent( p -> {
                    //if(processedRefNums.contains(p.refnum)==false
                    //&& processedIndicesOfRefnumMap.)


                    //Integer processedEntryVal = processedEntries.get(p.refnum);

                    //if(
                            //processedEntryVal == null ||
                                   // (processedEntryVal!=p.index))
                    Pair<Integer,Integer> ePair = new Pair<>(p.refnum,p.index);
                    if(!processedEntries.contains(ePair))
                    {
                        //processedEntries.put(p.refnum, p.index);
                        processedEntries.add(ePair);
                        this.photos.add(p);
                    }
                });
            }

            Log.d("Stream", " Stream photos count: "+ this.photos.size());
            for (ArFilePhoto a: this.photos
            ) {

                Log.d("Stream", " Stream photos : "+ a.file.getName());
            }
        }

        // ---------------------------

        // ArFileVideo:---------------------------

        {

            // Since we can have more than one photo for a specific scenarios, then
            // the 'refnum' and the 'index' must be checked to check for the update
            // version of a file.
            // There can be muliple files (updates) for a specific index of the refnum.

            // ar_typevideo_mag1_refnum2_index0_uv1

            List<Pair<Integer, Integer>> processedEntries = new ArrayList<>();

            Supplier<Stream<ArFileVideo>> streamSupplier = () -> this.candidateFiles.videos.stream();


            for (ArFileVideo item: this.candidateFiles.videos ) {

                streamSupplier.get().filter(f ->{
                    return f.refnum == item.refnum && f.index == item.index ;
                }).max((val1, val2) -> {
                    return ((Integer)val1.uv).compareTo(val2.uv);
                }).ifPresent( p -> {

                    Pair<Integer,Integer> ePair = new Pair<>(p.refnum,p.index);
                    if(!processedEntries.contains(ePair))
                    {
                        processedEntries.add(ePair);
                        this.videos.add(p);
                    }
                });
            }

            Log.d("Stream", " Stream videos count: "+ this.videos.size());
            for (ArFileVideo a: this.videos
            ) {

                Log.d("Stream", " Stream videos : "+ a.file.getName());
            }
        }

        // ---------------------------


        // ArFileModel3D:---------------------------

        {

            // Since we can have more than one photo for a specific scenarios, then
            // the 'refnum' and the 'index' must be checked to check for the update
            // version of a file.
            // There can be muliple files (updates) for a specific index of the refnum.

            // ar_type3d_mag1_refnum3_index0_uv1

            List<Pair<Integer, Integer>> processedEntries = new ArrayList<>();

            Supplier<Stream<ArFileModel3D>> streamSupplier = () -> this.candidateFiles.models3D.stream();


            for (ArFileModel3D item: this.candidateFiles.models3D ) {

                streamSupplier.get().filter(f ->{
                    return f.refnum == item.refnum && f.index == item.index ;
                }).max((val1, val2) -> {
                    return ((Integer)val1.uv).compareTo(val2.uv);
                }).ifPresent( p -> {

                    Pair<Integer,Integer> ePair = new Pair<>(p.refnum,p.index);
                    if(!processedEntries.contains(ePair))
                    {
                        processedEntries.add(ePair);
                        this.models3D.add(p);
                    }
                });
            }

            Log.d("Stream", " Stream Models3d count: "+ this.models3D.size());
            for (ArFileModel3D a: this.models3D
            ) {

                Log.d("Stream", " Stream Models3d : "+ a.file.getName());
            }
        }

        // ---------------------------

        // ArFileSound:---------------------------

        {

            // Since we can have more than one photo for a specific scenarios, then
            // the 'refnum' and the 'index' must be checked to check for the update
            // version of a file.
            // There can be muliple files (updates) for a specific index of the refnum.

            // ar_typesound_mag1_refnum3_index0_uv1

            List<Pair<Integer, Integer>> processedEntries = new ArrayList<>();

            Supplier<Stream<ArFileSound>> streamSupplier = () -> this.candidateFiles.sounds.stream();


            for (ArFileSound item: this.candidateFiles.sounds) {

                streamSupplier.get().filter(f ->{
                    return f.refnum == item.refnum && f.index == item.index;
                }).max((val1, val2) -> {
                    return ((Integer)val1.uv).compareTo(val2.uv);
                }).ifPresent( p -> {

                    Pair<Integer,Integer> ePair = new Pair<>(p.refnum,p.index);
                    if(!processedEntries.contains(ePair))
                    {
                        processedEntries.add(ePair);
                        this.sounds.add(p);
                    }
                });
            }

            Log.d("Stream", " Stream Sounds count: "+ this.sounds.size());
            for (ArFileSound a: this.sounds
            ) {

                Log.d("Stream", " Stream Sounds : "+ a.file.getName());
            }
        }

        // ---------------------------



    }


    private int getARFilePartValueInt(String filename, String part)
    {
        int retValue=-1;

        try
        {
            int elemIndexStart = -1, elemIndexEnd = -1;
            String element = "_" + part;

            elemIndexStart = filename.indexOf(element);
            elemIndexEnd = filename.indexOf("_", elemIndexStart + element.length());

            if (elemIndexEnd == -1)
                retValue = Integer.parseInt(filename.substring(elemIndexStart + element.length()).trim()) ;
            else if (elemIndexEnd != elemIndexStart + element.length())
                retValue = Integer.parseInt(filename.substring(elemIndexStart + element.length(), elemIndexEnd ).trim());

        }
        catch (Exception e) { Log.e("ar filename", "ar filename conversion error: " + e.getMessage()); }

        Log.d("json", "ar filename:" + filename + " / " + part +": " + retValue);

        return retValue;
    }

    private boolean getARFilePartValueBool(String filename, String part)
    {
        boolean retValue=false;

        try
        {
            int elemIndexStart = -1, elemIndexEnd = -1;
            String element = "_" + part;

            elemIndexStart = filename.indexOf(element);
            elemIndexEnd = filename.indexOf("_", elemIndexStart + element.length());

            if (elemIndexEnd == -1)
                retValue = Integer.parseInt(filename.substring(elemIndexStart + element.length()).trim()) == 1;
            else if (elemIndexEnd != elemIndexStart + element.length())
                retValue = Integer.parseInt(filename.substring(elemIndexStart + element.length(), elemIndexEnd).trim()) == 1;

        }
        catch (Exception e) { Log.e("ar filename", "ar filename conversion error: " + e.getMessage()); }

        Log.d("json", "ar filename:" + filename + " / " + part +": " + retValue);

        return retValue;
    }


}
