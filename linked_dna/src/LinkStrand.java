public class LinkStrand implements IDnaStrand {

    private Node myfirst =new Node("");
    private Node mylast;

    public static LinkStrand EMPTYSTRAND = new LinkStrand();

    public LinkStrand(Node DNA) {
        this.myfirst = DNA;
    }

    public LinkStrand() {

    }

    @Override
    public IDnaStrand cutWith(String enzyme) {

        int pos = myfirst.info.indexOf(enzyme);
        if (pos == -1)
            return EMPTYSTRAND;

        // found occurrence of enzyme, cleave, and replace
        IDnaStrand ret = new LinkStrand();
        myfirst.info=myfirst.info.substring(pos + enzyme.length());
        initializeFrom(myfirst.info.substring(0, pos));
        return ret;
    }


    @Override
    public IDnaStrand cutAndSplice(String enzyme, String splicee) {
        LinkStrand linkStrand = null;
        int pos=0;
        int lastPos=-1;
        String search=myfirst.info;

        boolean first=true;

        while((pos=search.indexOf(enzyme,pos)) >= 0){
            if ((first)){
                linkStrand=new LinkStrand();

                first=false;
            }else {

                Node node=new Node(myfirst.info.substring(pos+myfirst.info.indexOf(enzyme))+splicee);

                linkStrand.myfirst=node;


            }

            pos++;
        }

        return linkStrand;
    }

    @Override
    public long size() {
//
//        int count=0;
//        while (myfirst.next !=null)
//            count+=myfirst.next.info.length();

        return myfirst.info.length();
    }

    @Override
    public void initializeFrom(String source) {
       myfirst.info=source;
    }

    @Override
    public String strandInfo() {
        return this.getClass().getName();
    }

    @Override
    public IDnaStrand append(IDnaStrand dna) {

        if (dna instanceof LinkStrand) {
            LinkStrand ss = (LinkStrand) dna;
            StringBuilder sb = new StringBuilder(myfirst.info);
            sb.append(ss.myfirst.info);
            myfirst.info = sb.toString();
            return this;
        } else {
            return append(dna.toString());
        }
    }

    @Override
    public IDnaStrand append(String dna) {
         myfirst.next=new Node(dna);
        return this;
    }

    class  Node{
        private String info;
        Node next;

        public Node(String info, Node next) {
            this.info = info;
            this.next = next;
        }

        public Node(String info) {
            this(info,null);
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
