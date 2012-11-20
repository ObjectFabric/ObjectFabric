///
/// This file is part of ObjectFabric (http://objectfabric.org).
///
/// ObjectFabric is licensed under the Apache License, Version 2.0, the terms
/// of which may be found at http://www.apache.org/licenses/LICENSE-2.0.html.
///
/// Copyright ObjectFabric Inc.
///
/// This file is provided AS IS with NO WARRANTY OF ANY KIND, INCLUDING THE
/// WARRANTY OF DESIGN, MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.
///

namespace ObjectFabric
{
    /// <summary>
    /// Transactional object, corresponds to org.objectfabric.TObject in Java.
    /// </summary>
    public interface TObject
    {
        Resource Resource { get; }

        Workspace Workspace { get; }
    }
}
