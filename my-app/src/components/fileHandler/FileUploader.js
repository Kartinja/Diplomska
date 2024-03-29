import React, {useRef} from "react";
import {Button, Input} from "@mui/material";

const FileUploader = ({onFileSelect}) => {
    const fileInput = useRef(null);

    const handleFileInput = (e) => {
        const file = e.target.files[0];
        onFileSelect(file);
    }
    return(
        <div>


            <input type='file' onChange={handleFileInput}/>
            <button onClick={e=>fileInput.current && fileInput.current.click()}/>
        </div>
    );
}
export default FileUploader;