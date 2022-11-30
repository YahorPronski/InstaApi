import '../../../assets/styles/components/form/fields/fileupload.scss';

const FileUpload = ({label, onChange, required}) => {
    const labelCss = required ? "required" : "";

    return (
        <div>
            <label className={labelCss}>{label}</label>
            <input
                className="file-upload"
                type="file"
                onChange={onChange}/>
        </div>
    );
};

export default FileUpload;